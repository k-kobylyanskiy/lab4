public class Forma {
    static boolean isInside(float x, float y, float R) {
        if (x > 0 && y > 0) {
            return false;
        } else if (x < 0 && y > 0) {
            if (x >= -R/2 && y <= R) {
                return true;
            } else {
                return false;
            }
        } else if (x <= 0 && y <= 0) {
            if (x * x + y * y <= R * R/4) {
                return true;
            } else {
                return false;
            }
        } else {
            if (y >= x - R/2) {
                return true;
            } else {
                return false;
            }
        }
    }


    }
