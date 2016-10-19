<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:osd="http://osd.redhat.it/">
   <soapenv:Header/>
   <soapenv:Body>
      <osd:quoteShipping>
         <country>${body}</country>
      </osd:quoteShipping>
   </soapenv:Body>
</soapenv:Envelope>