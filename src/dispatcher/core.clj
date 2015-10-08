(ns dispatcher.core
  (:gen-class)
  (:require [clojure.edn :refer [read-string]]
            [clojure.java.io :as io]))

(defn read-config
  [entry]
  (as-> (slurp (io/resource "entry-points.edn")) *
        (read-string *)
        (* entry)))

(defn dispatch
  [{:keys [nspace func args] :as entry-map} cli-args]
  (require nspace)
  (apply (ns-resolve nspace (symbol func)) (concat args cli-args)))

(defn -main
  [entry & args]
  (-> (read-config entry)
      (dispatch args)))
