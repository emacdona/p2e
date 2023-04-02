(ns net.edmacdonald.pathfinder.entities
  (:require [clojure.string :as str]
            [com.rpl.specter :as sp]))


(def entity-types (atom #{}))
(def all-entities (atom {}))

(defn clear-global-state []
  (reset! entity-types #{})
  (reset! all-entities {}))

;; A macro that defines functions that
;; 1. Create new entities of a given type, and record them in the global entity database
;; 2. Decorate a collection of entities with additional metadata.
(defmacro entity-type
  [entity-type]
  `(let [type# (keyword (name '~entity-type))]
     (defn ~entity-type [name#]
       (let [key# (keyword (#(str/replace % #"\s+" "_")
                             name#))
             entity# {:key  key#
                      :name name#
                      :type type#}]

         ;; keep track of entity types we're creating
         (swap! entity-types #(conj % type#))
         (swap! all-entities #(assoc % type#
                                       (assoc (% type#)
                                         (entity# :key) entity#)))
         entity#))
     (defn ~(symbol (str entity-type "-decorate"))
       [entities# metadata-map# new-key#]
       (let [new-entities# (into-maps entities# metadata-map# new-key#)]
         (swap! all-entities
                #(assoc % type#
                          (reduce (fn [x# y#]
                                    (assoc x# (:key y#) y#))
                                  {}
                                  new-entities#)
                          ))
         new-entities#))
     ))

(defn into-maps
  "Given a sequence of Destination Maps (dest-maps), for each one, add a new key-value pair -- with
  key = dest-key; value = (keyed-source-data (destination-map :dest-id-key))"
  ( [dest-maps keyed-source-data dest-key]
   (into-maps dest-maps keyed-source-data dest-key :key))
  ( [dest-maps keyed-source-data dest-key dest-id-key]
   (map
     #(assoc % dest-key (keyed-source-data (% dest-id-key)))
     dest-maps)))

(defn to-map
  "Give a set of keyed entities (ie: each has a :key), create a mapping of :key's to entity"
  [set key]
  (apply merge
         (map (fn [x] (assoc {} (key x) x))
              set)))

(load "entity_abilities")
(load "entity_ancestries")
(load "entity_ancestry_entries")
(load "entity_backgrounds")
(load "entity_character_classes")
(load "entity_conditions")
(load "entity_feats")
(load "entity_languages")
(load "entity_skills")







