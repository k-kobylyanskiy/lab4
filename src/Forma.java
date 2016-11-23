public class Forma {
    static boolean isInside(float x, float y, float R) {
        if (x > 0 && y > 0) {
            return false;
        } else if (x < 0 && y > 0) {
            return (x >= -R/2 && y <= R);
        } else if (x <= 0 && y <= 0) {
            return (x * x + y * y <= R * R/4);
        } else {
            return (y >= x - R/2);
        }
    }
}
