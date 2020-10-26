package com.Notebook;
/*
简易记事本
 */
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
public class NoteBook extends JFrame implements ActionListener{
    //创建
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu jMenu;
    JMenuItem jMenuItem1;
    JMenuItem jMenuItem2;
    JScrollPane jScrollPane;
    public static void main(String[] args){
        NoteBook noteBook=new NoteBook();
    }
    //构造方法
    public NoteBook(){
        //初始化
        jTextArea=new JTextArea();
        jMenuBar=new JMenuBar();
        jMenu=new JMenu("File");
        //把多行文本框JTextArea放入滚动面板JScrollPane中
        jScrollPane=new JScrollPane(jTextArea);
        jMenuItem1=new JMenuItem("Open");
        jMenuItem1.setActionCommand("Open");
        jMenuItem2=new JMenuItem("Save");
        jMenuItem2.setActionCommand("Save");

        //把JMenuItem加入JMenu里
        jMenu.add(jMenuItem1);
        jMenu.add(jMenuItem2);
        //把JMenu加入JMenuBar
        jMenuBar.add(jMenu);

        //把JMenuBar加入到JFrame中
        this.setJMenuBar(jMenuBar);

        //把滚动面板JScrollPane放入JFrame中
        this.add(jScrollPane);

        //设置JFrame
        this.setName("NoteBook");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        //注册监听
        jMenuItem1.addActionListener(this);
        jMenuItem2.addActionListener(this);
    }

    //重写事件监听处理方法
    @Override
    public void actionPerformed(ActionEvent e) {
        //Open文件
        if(e.getActionCommand().equals("Open")){
            //新建一个文件选装框
            JFileChooser jFileChooser1=new JFileChooser();
            //设置文件选择框标题
            jFileChooser1.setDialogTitle("Open...");
            //显示默认窗口
            jFileChooser1.showOpenDialog(null);
            //显示
            jFileChooser1.setVisible(true);
            //得到用户选择的文件的路径
            String filePath = jFileChooser1.getSelectedFile().getAbsolutePath();
            //使用BufferedReader读取文件
            FileReader fileReader = null;
            BufferedReader  bufferedReader =null;
            try {
                fileReader = new FileReader(filePath);
                bufferedReader=new BufferedReader(fileReader);
                String text = "";
                String content="";
                //读取filepath里的文本内容
                while ((text = bufferedReader.readLine())!=null){
                    content=content+text+"\n";
                    System.out.println(content);
                    //把读取到的文本在JTextArea中显示
                    this.jTextArea.setText(content);
                }

            } catch (Exception a) {
                a.printStackTrace();
            }
            //关闭文件流
            finally {
                try {
                    //先开的后关
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        //save文件
        if(e.getActionCommand().equals("Save")){
            //同理，设置保存框
            JFileChooser jFileChooser2=new JFileChooser();
            jFileChooser2.setDialogTitle("Save...");
            jFileChooser2.showSaveDialog(null);
            jFileChooser2.setVisible(true);
            //同样得到文件保存路径
            String filePath = jFileChooser2.getSelectedFile().getAbsolutePath();
            //写入指定文件
            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;

            try {
                fileWriter=new FileWriter(filePath);
                bufferedWriter=new BufferedWriter(fileWriter);

                //把内容写入指定的文件中（filepath)
                bufferedWriter.write(this.jTextArea.getText());

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //关闭文件流
            finally {
                try {
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
