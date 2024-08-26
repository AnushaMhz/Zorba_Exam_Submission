package org.example;

public abstract class SelfOwnedFlat implements Property {
    public abstract float calculatePlot(float length, float breadth, float height);
    public abstract float finalPriceOfFlat();
}
class SelfOwnedOneBHKFlat  extends SelfOwnedFlat {

    @Override
    public float calculatePlot(float length, float breadth, float height) {
        return length * breadth * height;
    }

    @Override
    public float finalPriceOfFlat() {
        return 10000 * calculatePlot(10,20,30);
    }

    @Override
    public void displayPropertyDetails() {
        System.out.println("Price of one BHK Flat: " + finalPriceOfFlat());

    }
}
class SelfOwnedTwoBHKFlat extends SelfOwnedFlat {

    @Override
    public float calculatePlot(float length, float breadth, float height) {
        return length * breadth * height;
    }

    @Override
    public float finalPriceOfFlat() {
        return 30000 * calculatePlot(15,25,35);
    }

    @Override
    public void displayPropertyDetails() {
        System.out.println("Price of two BHK plot : " + finalPriceOfFlat());

    }
}

 class SelfOwnedThreeBHKFlat  extends SelfOwnedFlat {

    @Override
    public float calculatePlot(float length, float breadth, float height) {
        return length * breadth * height;
    }

    @Override
    public float finalPriceOfFlat() {
        return 50000 * calculatePlot(20,30,40);
    }

    @Override
    public void displayPropertyDetails() {
    System.out.println("Price of three BHK Flat : " + finalPriceOfFlat());
    }
}
