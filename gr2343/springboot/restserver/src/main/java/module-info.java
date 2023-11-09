module com.gr2343.springboot.restserver {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires com.gr2343.core;

    opens gr2343.springboot.restserver to spring.beans, spring.context, spring.web;
}

