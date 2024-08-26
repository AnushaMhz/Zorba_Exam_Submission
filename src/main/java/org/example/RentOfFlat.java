package org.example;

public abstract class RentOfFlat implements Property {
    public abstract int calculateRentOfFlat(int noOfPerson);
}

class RentOfOneBHKFlat extends RentOfFlat {

    @Override
    public int calculateRentOfFlat(int noOfPerson) {
        return 250 * noOfPerson;
    }

    @Override
    public void displayPropertyDetails() {
        System.out.println("Rent of One BHK Flat is : " + calculateRentOfFlat(3));

    }
}

class RentOfTwoBHKFlat  extends RentOfFlat {
    private String balcony;

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    @Override
    public int calculateRentOfFlat(int noOfPerson) {
        return 400 * noOfPerson;
    }

    @Override
    public void displayPropertyDetails() {
        System.out.println("Rent of Two BHK Flat with " + balcony + " is : " + calculateRentOfFlat(4));

    }
}
 class RentOfThreeBHKFlat extends RentOfFlat {
    private String balcony;

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    @Override
    public int calculateRentOfFlat(int noOfPerson) {
        return 600 * noOfPerson;
    }

    @Override
    public void displayPropertyDetails() {
    System.out.println("Rent of the Three BHK Flat with " + balcony + " is : " + calculateRentOfFlat(5)) ;
    }
}

