# dispatcher

Manages the entry points and initialization of an uberjar.

This dispatcher was built to share a single uberjar for multiple
responsibilities that are served by different JVM processes.


## Usage

In `project.clj`:

* Add `[com.farmlogs/dispatcher "1.0.3"]` to your dependencies.
* Set `:main` to `dispatcher.core`
* Add `:aot [#".*" dispatcher.core]` to your uberjar profile to
  include `dispatcher.core` to the namespaces to be compiled

In the resources path:

* Create a file entry-points.edn so that it's resource path is
  `/entry-points.edn` (see below for the format)

Then compile an uberjar and...

```bash
$ java -jar some-service-0.1.0-standalone.jar <entry-point> [args]
```


### AOT

Since `dispatcher.core` isn't referred to by any of your namespaces,
`:aot :all` won't cause it to be compiled. setting the `:aot` opoption
to `[#".*" dispatcher.core]` will act just like `:all`, but will also
include the `dispatcher.core` namespace.


### Sample `entry-points.edn`

```clojure
{"web" {:ns some-service.web
        :func -main}
 "worker" {:ns some-service.worker
           :func -main
           :args ["foo" "bar"]}}
```


### Examples
 

Simple example:

```bash
$ java -jar my-service-1.2.3.jar backend-worker
```

Example with arguments:

```bash
$ java -jar my-service-1.2.3.jar web 8080
```


## Uncaught Exceptions

This library also registers a handler for uncaught exceptions which
will print the thread name and dump the stacktrace, both to
stderr. See
[dispatcher.exceptions.uncaught](src/dispatcher/exceptions/uncaught.clj)
on how to modify that behavior.


## License

Copyright © 2017 FarmLogs

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
