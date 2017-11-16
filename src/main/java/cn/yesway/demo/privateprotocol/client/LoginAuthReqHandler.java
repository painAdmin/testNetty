package cn.yesway.demo.privateprotocol.client;

import cn.yesway.demo.privateprotocol.model.Header;
import cn.yesway.demo.privateprotocol.model.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthReqHandler extends ChannelHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginReq());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message=(NettyMessage)msg;
		if(message.getHeader()!=null && message.getHeader().getType()==(byte)2){
			System.out.println("received from server response! ");
		}
		ctx.fireChannelRead(msg);
	}

	private NettyMessage buildLoginReq(){
		NettyMessage message=new NettyMessage();
		Header header=new Header();
		header.setType((byte)1);
		message.setHeader(header);
		message.setBody("It is Request!");
		return message;
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	    ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
