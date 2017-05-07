# Project Name: Hair Salon

An app for a hair salon.

## Created by: Minneh Mugo

## Prerequisites

You will need the following things properly installed on your computer.

* JRE
* JDK

## Installation

* `git clone <https://github.com/Minneh/hair_salon>` this repository
* `cd superhero-squad`

## Running / Development

* `gradle run`

### Running Tests

* `gradle test`

### Building

* `gradle build`

### SQL

CREATE DATABASE hair_salon;

CREATE TABLE stylists (
  id serial PRIMARY KEY,
  name varchar
);

CREATE TABLE clients (
  id serial PRIMARY KEY,
  name varchar,
  stylist_id int
);
