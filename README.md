# wordnetjava
use of Java version of Wordnet in a Jetpack compose app

### Project status : Workable

## target audience
This project is for Jetpack Compose initiated user

## Presentation
WordNet is a large lexical database of English, organizing words into synsets (sets of synonyms) based on their meanings. Each synset represents a single concept, containing lemmas (base forms of words) and associated POS (Parts of Speech: noun, verb, adjective, adverb). Synsets include glosses (definitions) and examples. Words are linked via semantic relations like hypernyms (parents), hyponyms (children), antonyms, and more. The hierarchy starts with POS, which contains index words pointing to synsets, each storing lemmas and their relationships. This structure helps navigate word meanings and relationships efficiently.

This is the Java version workable in a Kotlin Jetpack compose app

## Overview
- 1 : main page word not setup
- 2 : part of speech : noun for the word "set"
- 3 : part of speech : verb for the word "set"
- 4 : part of speech : adjective for the word "set"

<img src="/screenshots/screen1.png" alt="main page word not setup" height="400">&emsp;
<img src="/screenshots/screen2.png" alt="part of speech noun for the word set" height="400">&emsp;
<img src="/screenshots/screen3.png" alt="part of speech verb for the word set" height="400">&emsp;
<img src="/screenshots/screen4.png" alt="part of speech adjective for the word set" height="400">&emsp;

# Init

## Libraries implementation
`jwnl-2.0.5.jar` needs some 3rd part libs to work that require some other ones and so on.

- create `libs` folder in `app` one and copy paste these 7 java libraries in it.
- modify in "app level" build.gradle.kts then sync
``` kotlin
dependencies {
	...
	implementation(files("libs/extjwnl-2.0.5.jar"))
    implementation(files("libs/extjwnl-data-wn31-1.2.jar"))
    implementation(files("libs/extjwnl-utilities-2.0.5.jar"))
    implementation(files("libs/slf4j-api-1.7.32.jar"))
    implementation(files("libs/slf4j-log4j12-1.7.32.jar"))
    implementation(files("libs/log4j-1.2.17.jar"))
    implementation(files("libs/concurrentlinkedhashmap-lru-1.3.2.jar"))
}
```
