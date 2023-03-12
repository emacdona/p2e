(ns net.edmacdonald.pathfinder.entities
  (:require [clojure.string :as str]))


(defn- key-name [str] (str/replace str #"\s+" "_"))

;; Someday, the functions that follow this macro will be more specialized. For now, they
;; are all the same. As long as they remain the same, I'd rather have clojure write them
;; for me... instead of having to do that work myself.
(defmacro key-name-map
  [name]
  `(defn ~name [name#]
     {
      :key  (keyword (key-name name#))
      :name name#
      :type (keyword (name '~name))
      }))

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







