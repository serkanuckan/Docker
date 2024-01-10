/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hazelcastexample;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Person {

    private String name;

    private Person() {
    }

    public static void main(String[] args) {
        // Hazelcast instance oluştur
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // IMap (distributed map) al
        IMap<String, Person> map = hazelcastInstance.getMap("persons");

        // Veri ekleme
        for (int i = 0; i < 10000; i++) {
            String key = "person" + i;
            Person person = new Person();
            person.setName("DummyName" + i);

            map.put(key, person);
        }

        // Veri alma
        for (int i = 0; i < 10000; i++) {
            String key = "person" + i;
            Person retrievedPerson = map.get(key);
            System.out.println("Retrieved Person: " + retrievedPerson.getName());
        }

        // Hazelcast instance'ı kapat
        hazelcastInstance.shutdown();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
