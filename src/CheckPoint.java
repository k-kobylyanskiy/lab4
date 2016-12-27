import sun.net.TelnetProtocolException;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class CheckPoint {

    public static byte[] floatsToBytes(float[] point) {
        ByteBuffer bb = ByteBuffer.allocate(point.length * 4);
        FloatBuffer fb = bb.asFloatBuffer();
        for (float i : point) fb.put(i);
        return bb.array();
    }

    public static int checkPoint(Point point){
        float points[] = new float[3];
        points[0] = point.getX();
        points[1] = point.getY();
        points[2] = (float)MainWindow.getR();


        byte[] sendData = floatsToBytes(points);
        int i = 0;
        DatagramSocket socket = null;

        try {
            InetAddress IPAddress = InetAddress.getByName("localhost");
            socket = new DatagramSocket();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 10950);
            socket.send(sendPacket);
        } catch (Exception e) {
            System.out.println("cannot send datagram");
            return 3;
        }

        try {
            socket.setSoTimeout(10);
        } catch (SocketException e) {
            e.printStackTrace();
        }

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                socket.receive(receivePacket);
            } catch (SocketTimeoutException e ){
                System.out.println("timeout");
                return 3;
            } catch (IOException e){

            }

            ByteArrayInputStream input = new ByteArrayInputStream(receiveData);
            DataInputStream dataInput = new DataInputStream(input);
            socket.close();

        try{
            i = dataInput.readInt();
        } catch (Exception e) {
            return 3;
        }

        return i;
    }
}
