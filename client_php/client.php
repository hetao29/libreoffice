<?php
define("ROOT",dirname(__FILE__)."/");
define("ROOT_PROTO_LIB",ROOT."../proto/php/");

require_once(ROOT."vendor/autoload.php");

//注册自己的类
spl_autoload_register(function($class){
	$root = ROOT_PROTO_LIB.str_replace("\\","/",$class).".php";
	require_once($root);
});

try{
	//call by grpc
	$client = new Libreoffice\LibreofficeClient("127.0.0.1:10001",[
		'credentials' => Grpc\ChannelCredentials::createInsecure()
	]);
	$request = new Libreoffice\Request();
	$request->setName("test.pptx");
	$request->setContent(file_get_contents(ROOT."/../test/test.pptx"));
	list($reply,$error) = $client->convert($request)->wait();
	if($reply){
		$files = $reply->getFiles();
		foreach($files as $file){
			file_put_contents(ROOT."/../test/".$file->getName(),$file->getContent());
		}

	}else{
		echo "Error\n";
		print_r($error);
	}

}catch(Exception $e){
	print_r($e);
}
