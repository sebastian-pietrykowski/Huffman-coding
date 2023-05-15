# Huffman-coding
A program enabling encoding and decoding of text files using Huffman coding.

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Usage](#usage)
* [How it works?](#how-it-works)
* [Results](#results)

## General Information
This project provides a Java implementation of Huffman coding for compressing and decompressing text files. Huffman coding is a lossless data compression algorithm that builds a binary tree based on the frequency of characters in a file. It assigns shorter codes to more frequently occurring characters, resulting in efficient compression.


## Technologies Used
- Java - version 11
- JUnit - version 5.8.1
- Maven - version 4.0.0


## Features
- Compressing text files
- Decompressing previously compressed text files


## Usage
To use the program you need to create an instance of `Huffman` class. Then, use `huffman` method with its arguments as:
- `pathToRootDir` - path to directory where text files to compress or decompress are stored,
- `compress` - true for compression, false for decompression.

In my project, there is already a class demonstrating the usage of the algorithm and containing the `main` method, called `LessonExampleGenerator`.

Example:
```
Huffman huffman = new Huffman();
huffman.huffman("lesson example", true);
huffman.huffman("lesson example", false);
```
Result files will be saved to `lesson example` directory.

Note:
- If directory contains more than one file to compress or decompress, all of them will be handled.
- File to be compressed doesn't have any special requirements, any extension is allowed.
- File with Huffman tree has postfix "-tree".
- File with compressed text has postfix "-compressed".
- File containing decompressed text has postfix "-decompressed".

Returns:
- for compression: sum of bits in files contatinig compressed text after compression without trees, using bytes for writing,
- for decompression: sum of characters in decompressed files.

## How it works?
Compression:
- checks if passed file exists and if it is a directory,
- finds files in directory to compress avoiding files with postfixes: -tree, -compressed,
- for each file:
    - builds Huffman tree, containing binary encoding of every used character - it is build so as to more frequently used characters had shorter binary codes what means they are closer to the root,
    - provided Huffman tree, builds recursively list containing encoding of every character,
    - saves tree to a text file,
    - reads whole text file and converts it to array of `chars`,
    - converts array of `chars` to binary form as `String` using encoding from the list, saves number of bits in result,
    - converts `String` to array of `bytes`, saves number of needed bits rounding its number up to 8 bits,
    - saves compressed text to a text file using byte-level encoding.

Decompression:
- checks if passed file exists and if it is a directory,
- finds pairs of compressed text (-compressed) and tree (-tree) with the same base name in a directory,
- for each pair:
    - reads compressed text as array of `bytes` and tree as `HuffmanTree` object from text files,
    - converts array of `bytes` to a decompressed `String` using Huffman tree and its property of prefix codes what allows to just go down the tree from the root to a node repeatedly to decode every character, saves number of decoded characters,
    - saves decompressed text to a text file.

## Results
For test purposes, I used a book called "Pan Tadeusz" containing 436 491 characters. The text file weighted 483 KB. With my program, I managed to reduce it to a combined weight of 282 KB with the compressed text file weighting 278 KB and the Huffman tree file - 4 KB, respectively. The size was cut down by nearly 41,6%.