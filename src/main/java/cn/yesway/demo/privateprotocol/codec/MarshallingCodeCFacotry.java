package cn.yesway.demo.privateprotocol.codec;


import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider; 
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder; 


public final class MarshallingCodeCFacotry {

	public static NettyMarshallingDecoder buildMarshallingDecoder(){
		MarshallerFactory marshallingFactory=Marshalling.getProvidedMarshallerFactory("serial");
	    MarshallingConfiguration configuration=new MarshallingConfiguration();
		configuration.setVersion(5);
		UnmarshallerProvider provider=new DefaultUnmarshallerProvider(marshallingFactory, configuration);
		NettyMarshallingDecoder decoder=new NettyMarshallingDecoder(provider,1024);
		return decoder;
	}
	
	public static NettyMarshallingEncoder buildMarshllingEncoder(){
		MarshallerFactory marshallerFactory=Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration configuration=new MarshallingConfiguration();
		configuration.setVersion(5);
		MarshallerProvider  provider=new DefaultMarshallerProvider(marshallerFactory,configuration);
		NettyMarshallingEncoder encoder = new NettyMarshallingEncoder(provider); 
		return encoder;
	}
}
