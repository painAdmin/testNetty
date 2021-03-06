package cn.yesway.demo.privateprotocol.codec;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yesway.demo.privateprotocol.model.Header;
import cn.yesway.demo.privateprotocol.model.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public final class NettyMessageDecoder extends LengthFieldBasedFrameDecoder{

	private NettyMarshallingDecoder  marshallingDecoder;
	public NettyMessageDecoder(int maxFrameLength,int lengthFieldOffset,int lengthFieldLength,int lengthAdjustment,int initialBytesToStrip)throws IOException{
		super(maxFrameLength,lengthFieldOffset,lengthFieldLength,lengthAdjustment,initialBytesToStrip);
		marshallingDecoder=MarshallingCodeCFacotry.buildMarshallingDecoder();              
	}
	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame=(ByteBuf)super.decode(ctx, in);
		if(frame == null){
			return null;
		}
		NettyMessage message=new NettyMessage();
		Header header=new Header();
		header.setCrcCode(frame.readInt());
		header.setLength(frame.readInt());
		header.setSessoinID(frame.readLong());
		header.setType(frame.readByte());
		header.setPriority(frame.readByte());
		int size =frame.readInt();
	    
		if(size>0){
			Map<String,Object> attch=new HashMap<String,Object>(size);
			int keySize=0;
			byte[] keyArray=null;
			String key=null;
			for(int i=0;i<size;i++){
				keySize=frame.readInt();
				keyArray=new byte[keySize];
				in.readBytes(keyArray);
				key=new String(keyArray,"UTF-8");
				attch.put(key, marshallingDecoder.decode(ctx,frame));
			}
			keyArray=null;
			key=null;
			header.setAttachment(attch);
		}
		if(frame.readByte()>0){
			message.setBody(marshallingDecoder.decode(ctx, frame));
		}
		message.setHeader(header);
		return message;
	}
	
	
}























