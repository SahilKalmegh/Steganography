// import java.awt.image.BufferedImage;
// import java.io.File;
// import javax.imageio.ImageIO;
// import java.nio.charset.StandardCharsets;

// public class LSBSteganography {

//     // Embed message into the image
//     public static void encodeImage(String inputImagePath, String outputImagePath, String message) {
//         try {
//             File inputFile = new File(inputImagePath);
//             if (!inputFile.exists()) {
//                 throw new IllegalArgumentException("Input file not found: " + inputImagePath);
//             }
//             BufferedImage image = ImageIO.read(inputFile);
//             byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
//             int messageLength = messageBytes.length;
//             int width = image.getWidth();
//             int height = image.getHeight();
//             int index = 0;
            
//             for (int y = 0; y < height; y++) {
//                 for (int x = 0; x < width; x++) {
//                     int pixel = image.getRGB(x, y);
//                     int alpha = (pixel >> 24) & 0xFF;
//                     int red = (pixel >> 16) & 0xFF;
//                     int green = (pixel >> 8) & 0xFF;
//                     int blue = pixel & 0xFF;
                    
//                     if (index < messageLength) {
//                         blue = (blue & 0xFE) | ((messageBytes[index] >> 7) & 0x01); // LSB Matching
//                         index++;
//                     }
                    
//                     int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
//                     image.setRGB(x, y, newPixel);
//                 }
//             }
//             ImageIO.write(image, "png", new File(outputImagePath));
//             System.out.println("Message encoded successfully!");
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     // Extract message from the image
//     public static void decodeImage(String imagePath, int messageLength) {
//         try {
//             File imageFile = new File(imagePath);
//             if (!imageFile.exists()) {
//                 throw new IllegalArgumentException("Image file not found: " + imagePath);
//             }
//             BufferedImage image = ImageIO.read(imageFile);
//             int width = image.getWidth();
//             int height = image.getHeight();
//             byte[] messageBytes = new byte[100];
//             int index = 0;
            
//             for (int y = 0; y < height; y++) {
//                 for (int x = 0; x < width; x++) {
//                     if (index >= messageLength) break;
//                     int pixel = image.getRGB(x, y);
//                     int blue = pixel & 0xFF;
                    
//                     messageBytes[index] = (byte) ((blue & 0x01) << 7);
//                     index++;
//                 }
//             }
//             String message = new String(messageBytes, StandardCharsets.UTF_8);
//             System.out.println("Extracted Message: " + message);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void main(String[] args) {
//         String folderPath = "C:/Users/LOQ/Desktop/steganography/";
//         String inputImagePath = folderPath + "output.jpg";
//         String outputImagePath = folderPath + "output2.png";
//         String secretMessage = "Hello, this is a hidden message!";
        
//         encodeImage(inputImagePath, outputImagePath, secretMessage);
//         decodeImage(outputImagePath, secretMessage.length());
//     }
// }

// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.util.Scanner;
// import javax.imageio.ImageIO;
// import java.nio.charset.StandardCharsets;

// public class LSBSteganography {

//     // Embed message into the image with password
//     public static void encodeImage(String inputImagePath, String outputImagePath, String message, String password) {
//         try {
//             File inputFile = new File(inputImagePath);
//             if (!inputFile.exists()) {
//                 throw new IllegalArgumentException("Input file not found: " + inputImagePath);
//             }
//             BufferedImage image = ImageIO.read(inputFile);
//             String messageWithPassword = password + ":" + message;
//             byte[] messageBytes = messageWithPassword.getBytes(StandardCharsets.UTF_8);
//             int width = image.getWidth();
//             int height = image.getHeight();
//             int index = 0;
            
//             for (int y = 0; y < height; y++) {
//                 for (int x = 0; x < width; x++) {
//                     if (index >= messageBytes.length) break;
//                     int pixel = image.getRGB(x, y);
//                     int alpha = (pixel >> 24) & 0xFF;
//                     int red = (pixel >> 16) & 0xFF;
//                     int green = (pixel >> 8) & 0xFF;
//                     int blue = pixel & 0xFF;
                    
//                     blue = (blue & 0xFE) | ((messageBytes[index] >> 7) & 0x01); // LSB Matching
//                     index++;
                    
//                     int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
//                     image.setRGB(x, y, newPixel);
//                 }
//             }
//             ImageIO.write(image, "png", new File(outputImagePath));
//             System.out.println("Message Embedded Successfully! Stego-media at: " + outputImagePath);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     // Extract message from the image with password verification
//     public static void decodeImage(String imagePath, String password) {
//         try {
//             File imageFile = new File(imagePath);
//             if (!imageFile.exists()) {
//                 throw new IllegalArgumentException("Image file not found: " + imagePath);
//             }
//             BufferedImage image = ImageIO.read(imageFile);
//             int width = image.getWidth();
//             int height = image.getHeight();
//             byte[] messageBytes = new byte[1000];
//             int index = 0;
            
//             for (int y = 0; y < height; y++) {
//                 for (int x = 0; x < width; x++) {
//                     if (index >= messageBytes.length) break;
//                     int pixel = image.getRGB(x, y);
//                     int blue = pixel & 0xFF;
                    
//                     messageBytes[index] = (byte) ((blue & 0x01) << 7);
//                     index++;
//                 }
//             }
//             String extractedMessage = new String(messageBytes, StandardCharsets.UTF_8).trim();
//             if (extractedMessage.startsWith(password + ":")) {
//                 System.out.println("Extracted Message: " + extractedMessage.substring(password.length() + 1));
//             } else {
//                 System.out.println("Password Incorrect!");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("Choose an option:\n1. Encrypt Message\n2. Decrypt Message");
//         int choice = scanner.nextInt();
//         scanner.nextLine();
        
//         if (choice == 1) {
//             System.out.print("Enter input image path: ");
//             String inputImagePath = scanner.nextLine();
//             System.out.print("Enter secret message: ");
//             String secretMessage = scanner.nextLine();
//             System.out.print("Enter output stego-media path: ");
//             String outputImagePath = scanner.nextLine();
//             System.out.print("Enter password: ");
//             String password = scanner.nextLine();

            
//             encodeImage(inputImagePath, outputImagePath, secretMessage, password);
//         } else if (choice == 2) {
//             System.out.print("Enter stego-media image path: ");
//             String stegoImagePath = scanner.nextLine();
//             System.out.print("Enter password: ");
//             String password = scanner.nextLine();
            
//             decodeImage(stegoImagePath, password);
//         } else {
//             System.out.println("Invalid option! Exiting...");
//         }
        
//         scanner.close();
//     }
// }


// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.util.Scanner;
// import javax.imageio.ImageIO;
// import java.nio.charset.StandardCharsets;

// public class LSBSteganography {

//     public static void encodeImage(String inputImagePath, String outputImagePath, String message, String password) {
//         try {
//             File inputFile = new File(inputImagePath);
//             if (!inputFile.exists()) {
//                 throw new IllegalArgumentException("Input file not found: " + inputImagePath);
//             }
//             BufferedImage image = ImageIO.read(inputFile);
//             String messageWithPassword = password + ":" + message;
//             byte[] messageBytes = messageWithPassword.getBytes(StandardCharsets.UTF_8);
//             int width = image.getWidth();
//             int height = image.getHeight();
//             int index = 0;
            
//             for (int y = 0; y < height; y++) {
//                 for (int x = 0; x < width; x++) {
//                     if (index >= messageBytes.length) break;
//                     int pixel = image.getRGB(x, y);
//                     int alpha = (pixel >> 24) & 0xFF;
//                     int red = (pixel >> 16) & 0xFF;
//                     int green = (pixel >> 8) & 0xFF;
//                     int blue = pixel & 0xFF;
                    
//                     blue = (blue & 0xFE) | ((messageBytes[index] >> 7) & 0x01);
//                     index++;
                    
//                     int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
//                     image.setRGB(x, y, newPixel);
//                 }
//             }
//             ImageIO.write(image, "png", new File(outputImagePath));
//             System.out.println("Message Embedded Successfully! Stego-media at: " + outputImagePath);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void decodeImage(String imagePath, String password) {
//         try {
//             File imageFile = new File(imagePath);
//             if (!imageFile.exists()) {
//                 throw new IllegalArgumentException("Image file not found: " + imagePath);
//             }
//             BufferedImage image = ImageIO.read(imageFile);
//             int width = image.getWidth();
//             int height = image.getHeight();
//             byte[] messageBytes = new byte[1000];
//             int index = 0;
            
//             for (int y = 0; y < height; y++) {
//                 for (int x = 0; x < width; x++) {
//                     if (index >= messageBytes.length) break;
//                     int pixel = image.getRGB(x, y);
//                     int blue = pixel & 0xFF;
                    
//                     messageBytes[index] = (byte) ((blue & 0x01) << 7);
//                     index++;
//                 }
//             }
//             String extractedMessage = new String(messageBytes, StandardCharsets.UTF_8).trim();
//             if (extractedMessage.startsWith(password + ":")) {
//                 System.out.println("Extracted Message: " + extractedMessage.substring(password.length() + 1));
//             } else {
//                 System.out.println("Password Incorrect!");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         while (true) {
//             System.out.println("Choose an option:\n1. Encrypt Message\n2. Decrypt Message\n3. Exit");
//             int choice = scanner.nextInt();
//             scanner.nextLine();
            
//             if (choice == 1) {
//                 System.out.print("Enter input image path: ");
//                 String inputImagePath = scanner.nextLine();
//                 System.out.print("Enter secret message: ");
//                 String secretMessage = scanner.nextLine();
//                 System.out.print("Enter output stego-media path: ");
//                 String outputImagePath = scanner.nextLine();
//                 System.out.print("Enter password: ");
//                 String password = scanner.nextLine();
                
//                 encodeImage(inputImagePath, outputImagePath, secretMessage, password);
//             } else if (choice == 2) {
//                 System.out.print("Enter stego-media image path: ");
//                 String stegoImagePath = scanner.nextLine();
//                 System.out.print("Enter password: ");
//                 String password = scanner.nextLine();
                
//                 decodeImage(stegoImagePath, password);
//             } else if (choice == 3) {
//                 System.out.println("Exiting...");
//                 break;
//             } else {
//                 System.out.println("Invalid option! Try again.");
//             }
//         }
//         scanner.close();
//     }
// }


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Base64;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class LSBSteganography {

    // Hash password using SHA-256 for better security
    private static String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedBytes).substring(0, 8); // Take first 8 characters
    }

    public static void encodeImage(String inputImagePath, String outputImagePath, String message, String password) {
        try {
            File inputFile = new File(inputImagePath);
            if (!inputFile.exists()) {
                System.err.println("Error: Input image not found.");
                return;
                //throw new IllegalArgumentException("\nInput file not found at : " + inputImagePath);
                //System.out.println("Input file not found: " + inputImagePath);
            }
            BufferedImage image = ImageIO.read(inputFile);
            if (image == null) {
                System.out.println("Error: Could not load image. Unsupported image format, make sure it's a supported format PNG, JPG, BMP, GIF.");
                return; // or throw an exception or System.err.println
            }
            
            // Append a unique end marker to stop reading extra data
            String hashedPassword = hashPassword(password);
            String messageWithPassword = hashedPassword + ":" + message + "|END|";
            byte[] messageBytes = messageWithPassword.getBytes(StandardCharsets.UTF_8);
            
            int width = image.getWidth();
            int height = image.getHeight();
            int totalPixels = width * height;

            if (messageBytes.length * 8 > totalPixels) {
                System.err.println("Error: Message is too large to fit in the image.");
                return;
            }

            int index = 0;
            outerloop:
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (index >= messageBytes.length * 8) break outerloop;  // Stop if all bits are embedded
                    
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xFF;
                    int red = (pixel >> 16) & 0xFF;
                    int green = (pixel >> 8) & 0xFF;
                    int blue = pixel & 0xFF;
                    
                    // Extract the correct bit from messageBytes
                    blue = (blue & 0xFE) | ((messageBytes[index / 8] >> (7 - (index % 8))) & 1);
                    index++;
                    
                    int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    image.setRGB(x, y, newPixel);
                }
            }
            ImageIO.write(image, "png", new File(outputImagePath));
            System.out.println("Message Embedded Successfully! Stego-media at: " + outputImagePath);
        } catch (Exception e) {
            System.err.println("Error during encoding: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    public static void decodeImage(String imagePath, String password) {
        try {
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                //throw new IllegalArgumentException("Image file not found: " + imagePath);
                System.err.println("Error: Image file not found.");
                return;
            }
            BufferedImage image = ImageIO.read(imageFile);
            if (image == null) {
                System.err.println("Error: Could not load image. Unsupported image format, make sure it's a supported format PNG, JPG, BMP, GIF.");
                return;
            }

            int width = image.getWidth();
            int height = image.getHeight();
            StringBuilder extractedBinary = new StringBuilder();
            
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    int blue = pixel & 0xFF;
                    extractedBinary.append(blue & 1); // Extract LSB
                }
            }
            
            // Convert binary data to text
            StringBuilder extractedText = new StringBuilder();
            for (int i = 0; i < extractedBinary.length(); i += 8) {
                if (i + 8 > extractedBinary.length()) break;  // Prevent out-of-bounds errors
                int byteValue = Integer.parseInt(extractedBinary.substring(i, i + 8), 2);
                extractedText.append((char) byteValue);
                if (extractedText.toString().endsWith("|END|")) {
                    extractedText.setLength(extractedText.length() - 5); // Remove the marker
                    break;
                }
            }
            
            String extractedMessage = extractedText.toString();
            String hashedPassword = hashPassword(password);

            if (extractedMessage.startsWith(hashedPassword + ":")) { //hashedPassword
                System.out.println("Message Extracted Sucssesfully!\n>> " + extractedMessage.substring(hashedPassword.length() + 1)); //hashedPassword
            } else {
                System.out.println("Password Incorrect!");
                //System.err.println("Error: Incorrect password or corrupted data.");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Error during decoding: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n...LSB Steganography...");
            System.out.println("Choose an option:\n1. Encrypt Message\n2. Decrypt Message\n3. Exit");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                System.out.println("\n...Encryption...");
                System.out.print("Note : Format supported PNG, JPG, BMP, GIF \n\nEnter input image path: ");
                String inputImagePath = scanner.nextLine();
                System.out.print("Enter secret message: ");
                String secretMessage = scanner.nextLine();
                System.out.print("Enter output stego-media path (include filename, e.g., stego.png): ");
                String outputImagePath = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                encodeImage(inputImagePath, outputImagePath, secretMessage, password);
                System.out.println("-----------------------");

            } else if (choice == 2) {
                System.out.println("\n...Decryption...");
                System.out.print("Note : Format supported PNG, JPG, BMP, GIF \n\nEnter stego-media image path: ");
                String stegoImagePath = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                
                decodeImage(stegoImagePath, password);
                System.out.println("-------------------");
            } else if (choice == 3) {
                System.out.println("Exiting Program...");
                break;
            } else {
                System.out.println("Invalid option! Try again.");
            }
        }
        scanner.close();
    }
}


