# Pistole

A collection of tools for working with - investigating/debugging typical, legacy
enterprise applications.

## C001 - Fixed-width Layout Specs Importer
C001 is a tool to import the fixed width layout specs used by my company for reading
and writing fixed width files into a Clojure data representation.

(read-specs s) reads the layout specs in s and returns the layout specs as
a clojure data structure. 

The Clojure data representation can be used by other tools for processing the
files of the corresponding fixed-width format.

## C002 - Fixed-width Header Generators
Generates standard fixed-width headers used in messages in an enterprise.

## C003 - Online Message Header Generator
Generates the message header required by a service provider.

## C004 - Fixed-width File Inspector
Display fixed-width file in a user-friendly way:
* Display column separators
* Display column labels
* Hide or Grey out padding characters
* Display date with day-month-year separator
* Provide horizontal scrolling with selected fixed columns

## C005 - Comm Logs Inspector
* Display request-response msg together
* Display indicators for request without response (time out cases)

## C006 - MQ Message Simulator
* Send and receive message on any request/response queues. 

## C007 - Web Message Simulator
* Send and receive message to any web-service endpoints

## C008 - MQ Message Generator
* Generate MQ Message 

## C009 - Web-service message Generator
* Generate Web service message

## Usage

FIXME

## License

Copyright © 2019 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
