import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjReader {

    public static class Model {
        public List<float[]> vertices; // Список вершин
        public List<float[]> normals; // Список нормалей
        public List<float[]> texCoords; // Список текстурных координат

        public Model() {
            vertices = new ArrayList<>();
            normals = new ArrayList<>();
            texCoords = new ArrayList<>();
        }
    }

    public static Model read(String filePath) {
        Model model = new Model();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                if (tokens.length > 0) {
                    switch (tokens[0]) {
                        case "v": // Вершина
                            float[] vertex = new float[3];
                            vertex[0] = Float.parseFloat(tokens[1]);
                            vertex[1] = Float.parseFloat(tokens[2]);
                            vertex[2] = Float.parseFloat(tokens[3]);
                            model.vertices.add(vertex);
                            break;
                        case "vn": // Нормаль
                            float[] normal = new float[3];
                            normal[0] = Float.parseFloat(tokens[1]);
                            normal[1] = Float.parseFloat(tokens[2]);
                            normal[2] = Float.parseFloat(tokens[3]);
                            model.normals.add(normal);
                            break;
                        case "vt": // Текстурные координаты
                            float[] texCoord = new float[2];
                            texCoord[0] = Float.parseFloat(tokens[1]);
                            texCoord[1] = Float.parseFloat(tokens[2]);
                            model.texCoords.add(texCoord);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    public static void main(String[] args) {

        String filePath = "C:\\Users\\gg\\Desktop\\obj-m\\Iphone.obj";
        Model model = ObjReader.read(filePath);

        System.out.println("Vertices:");
        for (float[] vertex : model.vertices) {
            System.out.printf("v: %.2f, %.2f, %.2f%n", vertex[0], vertex[1], vertex[2]);
        }

        System.out.println("Normals:");
        for (float[] normal : model.normals) {
            System.out.printf("vn: %.2f, %.2f, %.2f%n", normal[0], normal[1], normal[2]);
        }

        System.out.println("Texture Coordinates:");
        for (float[] texCoord : model.texCoords) {
            System.out.printf("vt: %.2f, %.2f%n", texCoord[0], texCoord[1]);
        }
    }
}
