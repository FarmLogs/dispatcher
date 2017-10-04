(ns dispatcher.core
  (:gen-class)
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :refer [join]]
            [dispatcher.exceptions.uncaught
             :refer [register-uncaught-exception-handler]]))

(defn read-config
  []
  (if-let [entry-points (io/resource "entry-points.edn")]
    (-> entry-points
        slurp
        (edn/read-string))
    (throw (ex-info "No entry-points.edn found on the classpath" {}))))

(defn dispatch
  [{:keys [ns func args] :as entry-map} cli-args]
  (require ns)
  (apply (ns-resolve ns func) (concat args cli-args)))

(defn legal-options-message
  [config-keys]
  (format "Please specify one of: %s." (join ", " config-keys)))

(defn validate
  [config entry-point]
  (when-not (contains? config entry-point)
    (throw (ex-info (format "%s isn't a valid option. %s"
                            entry-point
                            (legal-options-message (keys config)))
                    config)))
  config)

(defn -main
  ([]
   (println (legal-options-message (keys (read-config)))))
  ([entry-point & args]
   (register-uncaught-exception-handler)
   (-> (read-config)
       (validate entry-point)
       (get entry-point)
       (dispatch args))))
