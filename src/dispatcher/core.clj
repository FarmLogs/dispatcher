(ns dispatcher.core
  (:gen-class)
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn read-config
  [entry]
  (as-> (slurp (io/resource "entry-points.edn")) *
        (edn/read-string *)
        (* entry)))

(defn dispatch
  [{:keys [ns func args] :as entry-map} cli-args]
  (require ns)
  (apply (ns-resolve ns func) (concat args cli-args)))

(defn -main
  [entry & args]
  (-> (read-config entry)
      (dispatch args)))
