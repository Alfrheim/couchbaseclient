(ns couchbaseclient.core
  (:require [couchbase-clj.client :as c]
            [clojure.data.json :as json])
  (:gen-class))


(def orderfile (.getFile (clojure.java.io/resource "orderDTO.JSON")))

(def orderdocument (json/read-str (slurp (.toString orderfile)) :key-fn keyword))

;; https://github.com/otabat/couchbase-clj
;; https://github.com/clojure/data.json

(c/defclient client {:bucket "default"
                     :uris ["http://127.0.0.1:8091/pools"]})

(defn key->document
  [couch-key]
  (c/get-json client couch-key))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  
  (println (slurp (.getFile (clojure.java.io/resource "orderDTO.JSON")))))
