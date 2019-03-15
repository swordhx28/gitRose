package cn.nxj;
class Aclass{

void go(){

System.out.println("Aclass");

}

}

public class Bclass extends Aclass{

void go() {

System.out.println("Bclass");

}

public static void main(String args[]){

    Aclass a=new Aclass();

Aclass a1=new Bclass();

a.go();

a1.go();

}
}