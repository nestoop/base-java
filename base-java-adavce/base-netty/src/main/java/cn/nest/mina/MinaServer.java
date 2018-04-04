package cn.nest.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**-common
 * on 17-4-7.
 */
public class MinaServer {

    public static void main(String[] args) {

        IoAcceptor acceptor =new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());

        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new ServerHandler());

        acceptor.getSessionConfig().setReadBufferSize(2048);

        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        try {
            acceptor.bind(new InetSocketAddress(9099));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
