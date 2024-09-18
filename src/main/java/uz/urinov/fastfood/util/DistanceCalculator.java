package uz.urinov.fastfood.util;

public class DistanceCalculator {

    // Yer radiusi (km)
    private static final double EARTH_RADIUS = 6371.0;

    // Haversine formula
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // To'plamdagi qiymatlarni radianlarga aylantirish
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Latitud va longitudlar orasidagi farq
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Masofani hisoblash
        return EARTH_RADIUS * c;
    }


}

