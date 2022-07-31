import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class test {
    static int SIZE0 = 112;
    public static void main(String[] args) {
        image("C:\\Users\\Hatsuki\\Desktop\\keyword\\1.jpg",
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\frame0.png",
                "png",
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\001.png");
    }
    /**
     * 图片合成
     *
     * @param backgroundPath 底图
     * @param smallPath      小图
     * @param type           生成图片类型jpg,png...
     * @param resultPath     生成图片保存路径
     */
    public static void image(String backgroundPath, String smallPath, String type, String resultPath) {
        try {

            int sizeX = 99;
            int sizeY = 92;
            Assert.hasText(backgroundPath, "底图路径为空");
            Assert.hasText(smallPath, "小图路径为空");
            BufferedImage small = ImageIO.read(new File(smallPath));
            BufferedImage image = ImageIO.read(new File((backgroundPath)));
            BufferedImage image0 = ImageIO.read(new File(("C:\\Users\\Hatsuki\\Desktop\\keyword\\0.png")));
            Graphics g0 = image0.getGraphics();
            image = Thumbnails.of(image).size(sizeX,sizeY).asBufferedImage();

            g0.drawImage(image,SIZE0-sizeX,SIZE0-sizeY,image.getWidth(),image.getHeight(),null);

            //生成画笔
            Graphics g = image0.getGraphics();
            g.drawImage(small, 0, 0, small.getWidth() , small.getHeight(), null);


            ImageIO.write(image0, type, new File(resultPath));
        } catch (IOException e) {
            throw new RuntimeException("合成图片失败", e);
        }

    }

}
