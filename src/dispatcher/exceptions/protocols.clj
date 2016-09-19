(ns dispatcher.exceptions.protocols)

(defprotocol HandleException
  "This protocol is an extension point enabling services to define
  behavior specific to a particular exception type when it's caught by
  the uncaught exception handler."
  (handle [exception thread]))
