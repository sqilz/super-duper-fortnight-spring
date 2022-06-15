package com.example.patterns.creational;


interface CarPartsOrderService {
    String getManufacturer();
}

class BmwParts implements CarPartsOrderService {

    @Override
    public String getManufacturer() {
        return "Bayerische Motoren Werke AG.";
    }
}

class PeugeotParts implements CarPartsOrderService {

    @Override
    public String getManufacturer() {
        return "PSA Group.";
    }
}
class CarPartsOrderServiceFactory implements AbstractFactory<CarPartsOrderService> {
    public CarPartsOrderService getOrderService(String manufacturer) {
        return switch (manufacturer) {
            case "BMW" -> new BmwParts();
            case "Peugeot" -> new PeugeotParts();
            default -> null;
        };
    }

    @Override
    public CarPartsOrderService create(String manufacturer) {
        return getOrderService(manufacturer);
    }
}
class FactoryMethodPattern {
    public static void main(String[] args) {
        //CarPartsOrderServiceFactory factory = new CarPartsOrderServiceFactory();
        //CarPartsOrderService bmw = factory.getOrderService("BMW");
        //System.out.println(bmw.getManufacturer());
        //
        //CarPartsOrderService peugeot = factory.getOrderService("Peugeot");
        //System.out.println(peugeot.getManufacturer());
        //
        //
        //


        CarPartsOrderServiceFactory parts = (CarPartsOrderServiceFactory) FactoryProvider.getFactory("Parts");
        CarPartsOrderService bmw1 = parts.getOrderService("BMW");
    }
}


interface AbstractFactory<T> {
    T create(String brand);
}

class FactoryProvider {
    public static AbstractFactory getFactory(String type) {
        return switch (type) {
            case "Parts" -> new CarPartsOrderServiceFactory();
            case "Mechanic" -> new CarMechanicBookingServiceFactory();
            default -> null;
        };
    }
}



interface CarMechanicBookingService {
    String getMechanic();
}

class BmwZdzisioGarage implements CarMechanicBookingService {

    @Override
    public String getMechanic() {
        return "Zdzisio garage.";
    }
}

class PeugeotRomanGarage implements CarMechanicBookingService {

    @Override
    public String getMechanic() {
        return "RomanGarage.";
    }
}

class CarMechanicBookingServiceFactory implements AbstractFactory<CarMechanicBookingService> {
    public CarMechanicBookingService getGetBookingService(String name) {
        return switch (name) {
            case "Zdzisio" -> new BmwZdzisioGarage();
            case "Roman" -> new PeugeotRomanGarage();
            default -> null;
        };
    }

    @Override
    public CarMechanicBookingService create(String brand) {
        return getGetBookingService(brand);
    }
}
