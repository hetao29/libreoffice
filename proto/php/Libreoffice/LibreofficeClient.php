<?php
// GENERATED CODE -- DO NOT EDIT!

namespace Libreoffice;

/**
 * The greeter service definition.
 */
class LibreofficeClient extends \Grpc\BaseStub {

    /**
     * @param string $hostname hostname
     * @param array $opts channel options
     * @param \Grpc\Channel $channel (optional) re-use channel object
     */
    public function __construct($hostname, $opts, $channel = null) {
        parent::__construct($hostname, $opts, $channel);
    }

    /**
     * Sends a greeting
     * @param \Libreoffice\Request $argument input argument
     * @param array $metadata metadata
     * @param array $options call options
     */
    public function Convert(\Libreoffice\Request $argument,
      $metadata = [], $options = []) {
        return $this->_simpleRequest('/libreoffice.Libreoffice/Convert',
        $argument,
        ['\Libreoffice\Response', 'decode'],
        $metadata, $options);
    }

}
