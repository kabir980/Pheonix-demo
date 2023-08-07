package pheonix.smile.pheonixdemobird;


interface person{
    void greet();
}

class student implements person{
    @Override
    public void greet(){
        System.out.println("Hello");
    }
}
public class AnnonymousInnerClassDemo {
    public static void main(String[] args) {

        person person = new person() {
            @Override
            public void greet() {
                System.out.println("Namastey from Annonymous inner class");
            }

        };
        person.greet();


       // person student = new student();
       // student.greet();

    }

}
