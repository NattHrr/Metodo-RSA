package metodorsa;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese el mensaje a cifrar
        System.out.print("Ingrese el mensaje a cifrar: ");
        String messageStr = scanner.nextLine();

        scanner.close();

        // Convertir el mensaje a un número grande (BigInteger)
        BigInteger message = stringToBigInteger(messageStr);

        // Generar claves RSA
        RSAKeys rsaKeys = generateRSAKeys();
        System.out.println("Clave pública (n, e): " + rsaKeys.getPublicKey());
        System.out.println("Clave privada (n, d): " + rsaKeys.getPrivateKey());

        // Cifrar mensaje
        BigInteger encryptedMessage = encrypt(message, rsaKeys.getPublicKey());
        System.out.println("Mensaje cifrado: " + encryptedMessage);

        // Descifrar mensaje
        BigInteger decryptedMessage = decrypt(encryptedMessage, rsaKeys.getPrivateKey());
        System.out.println("Mensaje descifrado: " + bigIntegerToString(decryptedMessage));
    }

    public static class RSAKeys {
        private final BigInteger publicKey;
        private final BigInteger privateKey;

        public RSAKeys(BigInteger publicKey, BigInteger privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public BigInteger getPublicKey() {
            return publicKey;
        }

        public BigInteger getPrivateKey() {
            return privateKey;
        }
    }

    public static RSAKeys generateRSAKeys() {
        // Claves RSA más sencillas para propósitos educativos
        BigInteger p = new BigInteger("61");
        BigInteger q = new BigInteger("53");
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("17");
        BigInteger d = e.modInverse(phi);
        return new RSAKeys(n, d);
    }

    public static BigInteger encrypt(BigInteger message, BigInteger publicKey) {
        return message.modPow(publicKey, publicKey);
    }

    public static BigInteger decrypt(BigInteger encryptedMessage, BigInteger privateKey) {
        return encryptedMessage.modPow(privateKey, privateKey);
    }

    // Función para convertir una cadena de texto a BigInteger
    public static BigInteger stringToBigInteger(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append((int) c);
        }
        return new BigInteger(sb.toString());
    }

    // Función para convertir BigInteger a una cadena de texto
    public static String bigIntegerToString(BigInteger num) {
        String numStr = num.toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numStr.length(); i += 3) {
            String digits = numStr.substring(i, Math.min(i + 3, numStr.length()));
            sb.append((char) Integer.parseInt(digits));
        }
        return sb.toString();
    }
}
