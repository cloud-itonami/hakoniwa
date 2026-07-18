(ns hakoniwa.repository-contract-test
  (:require [clojure.edn :as edn] [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))
(deftest repository-boundary
  (let [c (edn/read-string (slurp "repository-contracts.edn"))]
    (is (= :edn (get-in c [:canonical :format])))
    (doseq [p ["manifest.edn" "schema.edn" "schema/hakoniwa-scenario-ontology.kotoba.edn"
               "wire/manifest.jsonld" "wire/tests/fixtures/wikidata_entities.json"]]
      (is (.isFile (io/file p)) p))
    (doseq [p (:forbidden-root-paths c)] (is (not (.exists (io/file p))) p))))
