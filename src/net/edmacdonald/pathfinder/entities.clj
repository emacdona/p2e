(ns net.edmacdonald.pathfinder.entities
  (:require [clojure.string :as str]
            [com.rpl.specter :as sp]))


(def entity-types (atom #{}))
(def all-entities (atom {}))

(defn clear-global-state []
  (reset! entity-types #{})
  (reset! all-entities {}))

;; Someday, the functions that follow this macro will be more specialized. For now, they
;; are all the same. As long as they remain the same, I'd rather have clojure write them
;; for me... instead of having to do that work myself.
(defmacro key-name-map
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
       (let [new-entities# (merge-meta entities# metadata-map# new-key#)]
         (swap! all-entities
                #(assoc % type#
                          (reduce (fn [x# y#]
                                    (assoc x# (:key y#) y#))
                                  {}
                                  new-entities#)
                          ))
         new-entities#))
     ))

(defn merge-meta
  [merge-target keyed-meta meta-key-name]
  (map
    #(assoc % meta-key-name (keyed-meta (% :key)))
    merge-target))

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







