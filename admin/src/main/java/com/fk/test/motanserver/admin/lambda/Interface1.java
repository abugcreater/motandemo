package com.fk.test.motanserver.admin.lambda;

@FunctionalInterface
 interface Interface1 {
 	int doubleNum(int i);
 
 	default int add(int x, int y) {
 		return x + y;
 	}
 
 	static int sub(int x, int y) {
 		return x - y;
 	}
 }
