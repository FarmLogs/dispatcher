(ns dispatcher.exceptions.uncaught
  (:require [clojure.tools.logging :as log]
            [dispatcher.exceptions.protocols :as p]))

;; Define default behavior for all exceptions. This can be overridden
;; by downstream code. It can also be extended to more specific types.
(extend-protocol p/HandleException
  Throwable
  (handle [exception thread]
    (log/error exception "Uncaught error on thread:" (.getName thread))))

(defn register-uncaught-exception-handler
  ([]
   (Thread/setDefaultUncaughtExceptionHandler
    (reify Thread$UncaughtExceptionHandler
      (uncaughtException [_ thread throwable]
        (p/handle throwable thread))))))
