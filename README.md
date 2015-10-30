# dispatcher

Manages the entry points and initialization of an uberjar.

Our services have multiple responsibilities. For example, the primary duty of `core-service` is to handle the essential CRUD API's, sync, etc... in a REST api role for all clients. *However*, it also has multiple *worker* jobs that need to run such as metrics, mail, etc...

This dispatcher was built to share a single uberjar for these multiple responsibilities.

## Usage

In the resources path:

* Create a file entry-points.edn so that it's resource path is `/entry-points.edn`
  The convention will be to ensure that project.clj has the line `:resource-paths ["src/resource"]`
  then place the entry-points.edn file there. This will ensure that there is a constant place where
  developers can look to find the entry points.

In `project.clj`:

* Set `:main` to `dispatcher.core`
* Add `dispatcher` as a dependency

```bash
$ java -jar some-service-0.1.0-standalone.jar <entry-point> [args]
```

### Examples

Simple example:

```bash
$ java -jar core-service-1.2.3.jar mail-worker
```

Example with arguments:

```bash
$ java -jar core-service-1.2.3.jar web 8080
```


### Sample `entry-points.edn`

```clojure
{"web" {:ns weather-service.web
        :func -main}
 "rain" {:ns worker.core
         :func -main
         :args ["weather-service.workers" "enqueue-email"]}
 "mrms-ingest" {:ns weather-service.worker.mrms-ingest
                :func -main}}
```
