package com.example.demo.service;


public interface IStorageService {

	byte[] loadFile(String fileName);

	String storeFile(String fileName, byte[] content); 

} 