import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        //leitura da imagem
        //InputStream inputStream = new FileInputStream("entrada/filme.jpg")
        //InputStream inputStream = new URL("").openStream()
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memória com transparência com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original pra nova imagem(em memória)
        Graphics2D graphics  = (Graphics2D)novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 128);
        graphics.setColor(Color.yellow);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 500, novaAltura - 80);

        //escrever a nova imagem e um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}
