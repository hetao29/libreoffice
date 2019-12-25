package libreoffice;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeter service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.27.0-SNAPSHOT)",
    comments = "Source: libreoffice/libreoffice.proto")
public final class LibreofficeGrpc {

  private LibreofficeGrpc() {}

  public static final String SERVICE_NAME = "libreoffice.Libreoffice";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<libreoffice.LibreofficeOuterClass.Request,
      libreoffice.LibreofficeOuterClass.Response> getConvertMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Convert",
      requestType = libreoffice.LibreofficeOuterClass.Request.class,
      responseType = libreoffice.LibreofficeOuterClass.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<libreoffice.LibreofficeOuterClass.Request,
      libreoffice.LibreofficeOuterClass.Response> getConvertMethod() {
    io.grpc.MethodDescriptor<libreoffice.LibreofficeOuterClass.Request, libreoffice.LibreofficeOuterClass.Response> getConvertMethod;
    if ((getConvertMethod = LibreofficeGrpc.getConvertMethod) == null) {
      synchronized (LibreofficeGrpc.class) {
        if ((getConvertMethod = LibreofficeGrpc.getConvertMethod) == null) {
          LibreofficeGrpc.getConvertMethod = getConvertMethod =
              io.grpc.MethodDescriptor.<libreoffice.LibreofficeOuterClass.Request, libreoffice.LibreofficeOuterClass.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Convert"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  libreoffice.LibreofficeOuterClass.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  libreoffice.LibreofficeOuterClass.Response.getDefaultInstance()))
              .setSchemaDescriptor(new LibreofficeMethodDescriptorSupplier("Convert"))
              .build();
        }
      }
    }
    return getConvertMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LibreofficeStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibreofficeStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibreofficeStub>() {
        @java.lang.Override
        public LibreofficeStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibreofficeStub(channel, callOptions);
        }
      };
    return LibreofficeStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LibreofficeBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibreofficeBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibreofficeBlockingStub>() {
        @java.lang.Override
        public LibreofficeBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibreofficeBlockingStub(channel, callOptions);
        }
      };
    return LibreofficeBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LibreofficeFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibreofficeFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibreofficeFutureStub>() {
        @java.lang.Override
        public LibreofficeFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibreofficeFutureStub(channel, callOptions);
        }
      };
    return LibreofficeFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static abstract class LibreofficeImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void convert(libreoffice.LibreofficeOuterClass.Request request,
        io.grpc.stub.StreamObserver<libreoffice.LibreofficeOuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getConvertMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConvertMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                libreoffice.LibreofficeOuterClass.Request,
                libreoffice.LibreofficeOuterClass.Response>(
                  this, METHODID_CONVERT)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class LibreofficeStub extends io.grpc.stub.AbstractAsyncStub<LibreofficeStub> {
    private LibreofficeStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibreofficeStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibreofficeStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void convert(libreoffice.LibreofficeOuterClass.Request request,
        io.grpc.stub.StreamObserver<libreoffice.LibreofficeOuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConvertMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class LibreofficeBlockingStub extends io.grpc.stub.AbstractBlockingStub<LibreofficeBlockingStub> {
    private LibreofficeBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibreofficeBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibreofficeBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public libreoffice.LibreofficeOuterClass.Response convert(libreoffice.LibreofficeOuterClass.Request request) {
      return blockingUnaryCall(
          getChannel(), getConvertMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class LibreofficeFutureStub extends io.grpc.stub.AbstractFutureStub<LibreofficeFutureStub> {
    private LibreofficeFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibreofficeFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibreofficeFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<libreoffice.LibreofficeOuterClass.Response> convert(
        libreoffice.LibreofficeOuterClass.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getConvertMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONVERT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LibreofficeImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LibreofficeImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONVERT:
          serviceImpl.convert((libreoffice.LibreofficeOuterClass.Request) request,
              (io.grpc.stub.StreamObserver<libreoffice.LibreofficeOuterClass.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LibreofficeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LibreofficeBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return libreoffice.LibreofficeOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Libreoffice");
    }
  }

  private static final class LibreofficeFileDescriptorSupplier
      extends LibreofficeBaseDescriptorSupplier {
    LibreofficeFileDescriptorSupplier() {}
  }

  private static final class LibreofficeMethodDescriptorSupplier
      extends LibreofficeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LibreofficeMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LibreofficeGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LibreofficeFileDescriptorSupplier())
              .addMethod(getConvertMethod())
              .build();
        }
      }
    }
    return result;
  }
}
