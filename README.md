# Steganography
# LSB Steganography in Java
Steganography project under AICTE internship

## Overview
This project implements **Least Significant Bit (LSB) Steganography** in Java, allowing users to hide secret messages within images securely. It also provides password protection to ensure that only authorized users can extract hidden messages.

## Features
- **Embed messages** into images using LSB technique.
- **Extract hidden messages** with password verification.
- **Password hashing** for security.
- **Supports PNG, JPG, BMP, and GIF images** for encoding and decoding.

## Technologies Used
- Java
- BufferedImage (Java Image Processing)
- MessageDigest (SHA-256 for password hashing)
- Base64 Encoding
- Scanner (User input handling)

## How It Works
### Encoding Process
1. Enter the cover image path.
2. Enter a secret message.
3. Enter a path with a file name where the Stego-media get stored.
4. The secret message is concatenated with a hashed password.
5. The combined message is converted into binary.
6. Each bit is embedded into the LSB of pixel values in the image.
7. The modified image is saved as a stego-image.

### Decoding Process
1. Enter Stego-media path.
2. Enter Password.
3. Then algorithm extracts bits from the LSB of the image pixels.
4. Converts the binary back to text.
5. Verifies the password.
6. Displays the extracted message if the password matches.

## Installation & Usage
### Prerequisites
- Java Development Kit (JDK) installed.
- A PNG, JPG, BMP or GIF image for encoding.

### Running the Program
1. Clone the repository:
   ```sh
   git clone https://github.com/Sahilkalmegh/Steganography-Java.git
   cd Steganography-Java
   ```
2. Compile the Java program:
   ```sh
   javac LSBSteganography.java
   ```
3. Run the program:
   ```sh
   java LSBSteganography
   ```
4. Follow on-screen instructions to:
   - **Encrypt a message** into an image.
   - **Decrypt a message** from a stego-image.

## Example Usage
### Choose option
```
...LSB Steganography...
Choose an option:
1. Encrypt Message
2. Decrypt Message
3. Exit
>> 
```
### Encrypting a Message
```
...Encryption...
Note : Format supported PNG, JPG, BMP, GIF

Enter input image path: input.png
Enter secret message: Hello World!
Enter output stego-media path: output.png
Enter password: securepass
Message Embedded Successfully!
```

### Decrypting a Message
```
...Decryption...
Note : Format supported PNG, JPG, BMP, GIF

Enter stego-media image path: output.png
Enter password: securepass
Message Extracted Successfully!
>> Hello World!
```

## Future Enhancements
- Support for all **image formats** (WebP, etc.).
- Implement **AES encryption** for enhanced security.
- Develop a **GUI-based application** for ease of use.



