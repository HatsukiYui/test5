import cn.hutool.core.img.gif.AnimatedGifEncoder;
import net.coobird.thumbnailator.Thumbnails;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class test2 {
    static double P = 2;
    static  double SIZE0 = 112*P;

    public static void main(String[] args) {

        InputStream i1 = image(96*P,92*P,0*P,0*P,0*P,
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\1.jpg",
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\frame0.png"
        );

        InputStream i2 = image(102*P,60*P,18*P,5*P,5*P,
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\1.jpg",
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\frame1.png"
        );

        InputStream i3 = image(112*P, 40*P, 28*P, 12*P, 10*P,
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\1.jpg",
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\frame2.png"
        );

        InputStream i4 = image(102*P, 60*P, 18*P, 5*P, 5*P,
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\1.jpg",
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\frame3.png"
        );
        InputStream i5 = image(96*P, 92*P, 0*P, 0*P, 0*P,
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\1.jpg",
                "C:\\Users\\Hatsuki\\Desktop\\keyword\\frame4.png"
        );


        InputStream[] pic = {i1,i2,i3,i4,i5};

        String newPic="C:\\Users\\Hatsuki\\Desktop\\keyword\\呜呜呜.gif";
        jpgToGif(pic,newPic,50);
    }

    /**
     * 图片合成
     * @param sizeX
     * @param sizeY
     * @param drop
     * @param offsetX
     * @param offsetY
     * @param backgroundPath
     * @param topPath
     * @return
     */
    public static InputStream image( double sizeX, double sizeY, double drop, double offsetX, double offsetY,String backgroundPath, String topPath) {
        try {

            BufferedImage top = ImageIO.read(new File(topPath));
            BufferedImage image = ImageIO.read(new File((backgroundPath)));

            //新建112*112的透明背景
            BufferedImage image0 = new BufferedImage((int)SIZE0,(int) SIZE0, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image0.createGraphics();
            image0 = graphics.getDeviceConfiguration().createCompatibleImage((int) SIZE0, (int)SIZE0, Transparency.TRANSLUCENT);

            Graphics g = image0.getGraphics();
            image = Thumbnails.of(image).scale(2f).outputQuality(1).asBufferedImage();



            g.drawImage(image,(int)(SIZE0-sizeX-offsetX),(int)(SIZE0-sizeY-offsetY),(int) (sizeX+offsetX*3), (int) (sizeY+offsetY*1.3),null);

            //手
            top = Thumbnails.of(top).scale(2f).outputQuality(1).asBufferedImage();

            g.drawImage(top, 0, (int)drop, top.getWidth() , top.getHeight(), null);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image0, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;




        } catch (IOException e) {
            throw new RuntimeException("合成图片失败", e);
        }

    }

    private synchronized static void jpgToGif(InputStream pic[], String newPic, int playTime) {
        try {
            AnimatedGifEncoder e = new AnimatedGifEncoder();
            e.setRepeat(0);
            e.start(newPic);
            BufferedImage src[] = new BufferedImage[pic.length];
            for (int i = 0; i < src.length; i++) {
                e.setDelay(playTime); //设置播放的延迟时间

                src[i] = ImageIO.read( pic[i]); // 读入需要播放的jpg文件
                e.addFrame(src[i]);  //添加到帧中
            }
            e.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
