import java.util.HashMap;
import java.util.Random;

public class urlkey {
    private static final String BASE_URL = "https://short.ly/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 8;

    private HashMap<String, String> shortToLong = new HashMap<>();
    private HashMap<String, String> longToShort = new HashMap<>();
    private Random random = new Random();

    // Generate a short key
    private String generateShortKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHAR_SET.length());
            sb.append(CHAR_SET.charAt(index));
        }
        return sb.toString();
    }

    // Shorten a URL
    public String shortenUrl(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return BASE_URL + longToShort.get(longUrl);
        }

        String shortKey;
        do {
            shortKey = generateShortKey();
        } while (shortToLong.containsKey(shortKey));

        shortToLong.put(shortKey, longUrl);
        longToShort.put(longUrl, shortKey);

        return BASE_URL + shortKey;
    }

    // Expand a short URL
    public String expandUrl(String shortUrl) {
        String shortKey = shortUrl.replace(BASE_URL, "");
        return shortToLong.getOrDefault(shortKey, "URL not found");
    }

    // Main method to test
    public static void main(String[] args) {
        urlkey urlShortener = new urlkey();

        String longUrl = "https://medium.com/@sandeep4.verma/system-design-scalable-url-shortener-service-like-tinyurl-106f30f23a82";
        String shortUrl = urlShortener.shortenUrl(longUrl);

        System.out.println("Shortened: " + shortUrl);
        System.out.println("Expanded: " + urlShortener.expandUrl(shortUrl));
    }
}
