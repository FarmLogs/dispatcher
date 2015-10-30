(ns dispatcher.core
  (:gen-class)
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :refer [join]]))

(defn read-config
  []
  (-> (io/resource "entry-points.edn")
      slurp
      (edn/read-string)))

(defn dispatch
  [{:keys [ns func args] :as entry-map} cli-args]
  (require ns)
  (apply (ns-resolve ns func) (concat args cli-args)))

(defn -main
  ([]
   (let [config-keys (keys (read-config))]
     (println "Please specify one of:" (join ", " config-keys) ".")))
  ([entry-point & args]
   (-> (read-config)
       (get entry-point)
       (dispatch args))))
