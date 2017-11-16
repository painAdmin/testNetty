package cn.yesway.demo.privateprotocol.codec;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;  
public class NettyMarshallingDecoder extends MarshallingDecoder {

	
   public NettyMarshallingDecoder(UnmarshallerProvider provider){
	   super(provider);
   }
   public NettyMarshallingDecoder(UnmarshallerProvider provider,int maxObjectSize){
	   super(provider,maxObjectSize);
   }
   public Object decode(ChannelHandlerContext arg0,ByteBuf arg1) throws Exception{
	   return super.decode(arg0,arg1);
   }
}
