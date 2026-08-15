# Java 3D Triangle Viewer

A simple Java Swing project that displays a colorful 3D triangle/tetrahedron and allows the user to rotate it using sliders.

## Features

* Displays a simple 3D object
* Uses Java Swing for the graphical interface
* Horizontal slider controls **heading**
* Vertical slider controls **pitch**
* Different triangle faces have different colors
* Triangle borders are drawn in white

## Technologies Used

* Java
* Java Swing
* AWT
* `Graphics2D`
* `Path2D`

## Project Structure

### `DemoViewer`

Main class responsible for:

* Creating the window
* Creating sliders
* Drawing the object
* Rotating the vertices

### `Vertex`

Represents a point in 3D space.

```java
Vertex(double x, double y, double z)
```

It stores:

* `x` → horizontal position
* `y` → vertical position
* `z` → depth

### `Triangle`

Represents one triangular face.

Each triangle contains:

* Three vertices
* One color

## How Rotation Works

The object is rotated using mathematical rotation formulas.

### Heading

Heading rotates the object around the **Y-axis**.

### Pitch

Pitch rotates the object around the **X-axis**.

The sliders change these values and call:

```java
repaint();
```

This redraws the object with its new rotation.

## How to Run

1. Save the file as:

```text
DemoViewer.java
```

2. Compile:

```bash
javac DemoViewer.java
```

3. Run:

```bash
java DemoViewer
```

## Controls

| Control       | Purpose             |
| ------------- | ------------------- |
| Bottom slider | Horizontal rotation |
| Right slider  | Vertical rotation   |

## Learning Purpose

This project is useful for learning:

* Java classes and objects
* Lists
* GUI programming
* Java Swing
* Graphics
* 3D coordinates
* Rotation mathematics
* Event listeners
* Basic computer graphics
