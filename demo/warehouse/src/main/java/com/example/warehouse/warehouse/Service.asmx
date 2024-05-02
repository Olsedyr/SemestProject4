﻿<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://tempuri.org/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:http="http://schemas.microsoft.com/ws/06/2004/policy/http" xmlns:msc="http://schemas.microsoft.com/ws/2005/12/wsdl/contract" xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" targetNamespace="http://tempuri.org/" name="IEmulatorService" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <xs:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:ser="http://schemas.microsoft.com/2003/10/Serialization/">
      <xs:element name="PickItem">
        <xs:complexType>
          <xs:sequence>
            <xs:element minOccurs="1" name="trayId" type="xs:int" />
          </xs:sequence>
        </xs:complexType>
      </xs:element>
      <xs:element name="PickItemResponse">
        <xs:complexType>
          <xs:sequence>
            <xs:element minOccurs="1" name="PickItemResult" nillable="true" type="xs:string" />
          </xs:sequence>
        </xs:complexType>
      </xs:element>
      <xs:element name="InsertItem">
        <xs:complexType>
          <xs:sequence>
            <xs:element minOccurs="1" name="trayId" type="xs:int" />
            <xs:element minOccurs="1" name="name" nillable="true" type="xs:string" />
          </xs:sequence>
        </xs:complexType>
      </xs:element>
      <xs:element name="InsertItemResponse">
        <xs:complexType>
          <xs:sequence>
            <xs:element minOccurs="1" name="InsertItemResult" nillable="true" type="xs:string" />
          </xs:sequence>
        </xs:complexType>
      </xs:element>
      <xs:element name="GetInventory">
        <xs:complexType>
          <xs:sequence />
        </xs:complexType>
      </xs:element>
      <xs:element name="GetInventoryResponse">
        <xs:complexType>
          <xs:sequence>
            <xs:element minOccurs="1" name="GetInventoryResult" nillable="true" type="xs:string" />
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    </xs:schema>
    <xs:schema attributeFormDefault="qualified" elementFormDefault="qualified" targetNamespace="http://schemas.microsoft.com/2003/10/Serialization/" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://schemas.microsoft.com/2003/10/Serialization/">
      <xs:element name="anyType" nillable="true" type="xs:anyType" />
      <xs:element name="anyURI" nillable="true" type="xs:anyURI" />
      <xs:element name="base64Binary" nillable="true" type="xs:base64Binary" />
      <xs:element name="boolean" nillable="true" type="xs:boolean" />
      <xs:element name="byte" nillable="true" type="xs:byte" />
      <xs:element name="dateTime" nillable="true" type="xs:dateTime" />
      <xs:element name="decimal" nillable="true" type="xs:decimal" />
      <xs:element name="double" nillable="true" type="xs:double" />
      <xs:element name="float" nillable="true" type="xs:float" />
      <xs:element name="int" nillable="true" type="xs:int" />
      <xs:element name="long" nillable="true" type="xs:long" />
      <xs:element name="QName" nillable="true" type="xs:QName" />
      <xs:element name="short" nillable="true" type="xs:short" />
      <xs:element name="string" nillable="true" type="xs:string" />
      <xs:element name="unsignedByte" nillable="true" type="xs:unsignedByte" />
      <xs:element name="unsignedInt" nillable="true" type="xs:unsignedInt" />
      <xs:element name="unsignedLong" nillable="true" type="xs:unsignedLong" />
      <xs:element name="unsignedShort" nillable="true" type="xs:unsignedShort" />
      <xs:element name="char" nillable="true" type="tns:char" />
      <xs:simpleType name="char">
        <xs:restriction base="xs:int" />
      </xs:simpleType>
      <xs:element name="duration" nillable="true" type="tns:duration" />
      <xs:simpleType name="duration">
        <xs:restriction base="xs:duration">
          <xs:pattern value="\-?P(\d*D)?(T(\d*H)?(\d*M)?(\d*(\.\d*)?S)?)?" />
          <xs:minInclusive value="-P10675199DT2H48M5.4775808S" />
          <xs:maxInclusive value="P10675199DT2H48M5.4775807S" />
        </xs:restriction>
      </xs:simpleType>
      <xs:element name="guid" nillable="true" type="tns:guid" />
      <xs:simpleType name="guid">
        <xs:restriction base="xs:string">
          <xs:pattern value="[\da-fA-F]{8}-[\da-fA-F]{4}-[\da-fA-F]{4}-[\da-fA-F]{4}-[\da-fA-F]{12}" />
        </xs:restriction>
      </xs:simpleType>
      <xs:attribute name="FactoryType" type="xs:QName" />
      <xs:attribute name="Id" type="xs:ID" />
      <xs:attribute name="Ref" type="xs:IDREF" />
    </xs:schema>
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://schemas.microsoft.com/2003/10/Serialization/Arrays" xmlns:ser="http://schemas.microsoft.com/2003/10/Serialization/" elementFormDefault="qualified" targetNamespace="http://schemas.microsoft.com/2003/10/Serialization/Arrays">
      <xs:import namespace="http://schemas.microsoft.com/2003/10/Serialization/" />
    </xs:schema>
  </wsdl:types>
  <wsdl:message name="IEmulatorService_PickItem_InputMessage">
    <wsdl:part name="parameters" element="tns:PickItem" />
  </wsdl:message>
  <wsdl:message name="IEmulatorService_PickItem_OutputMessage">
    <wsdl:part name="parameters" element="tns:PickItemResponse" />
  </wsdl:message>
  <wsdl:message name="IEmulatorService_InsertItem_InputMessage">
    <wsdl:part name="parameters" element="tns:InsertItem" />
  </wsdl:message>
  <wsdl:message name="IEmulatorService_InsertItem_OutputMessage">
    <wsdl:part name="parameters" element="tns:InsertItemResponse" />
  </wsdl:message>
  <wsdl:message name="IEmulatorService_GetInventory_InputMessage">
    <wsdl:part name="parameters" element="tns:GetInventory" />
  </wsdl:message>
  <wsdl:message name="IEmulatorService_GetInventory_OutputMessage">
    <wsdl:part name="parameters" element="tns:GetInventoryResponse" />
  </wsdl:message>
  <wsdl:portType name="IEmulatorService">
    <wsdl:operation name="PickItem">
      <wsdl:input wsam:Action="http://tempuri.org/IEmulatorService/PickItem" message="tns:IEmulatorService_PickItem_InputMessage" />
      <wsdl:output wsam:Action="http://tempuri.org/IEmulatorService/PickItemResponse" message="tns:IEmulatorService_PickItem_OutputMessage" />
    </wsdl:operation>
    <wsdl:operation name="InsertItem">
      <wsdl:input wsam:Action="http://tempuri.org/IEmulatorService/InsertItem" message="tns:IEmulatorService_InsertItem_InputMessage" />
      <wsdl:output wsam:Action="http://tempuri.org/IEmulatorService/InsertItemResponse" message="tns:IEmulatorService_InsertItem_OutputMessage" />
    </wsdl:operation>
    <wsdl:operation name="GetInventory">
      <wsdl:input wsam:Action="http://tempuri.org/IEmulatorService/GetInventory" message="tns:IEmulatorService_GetInventory_InputMessage" />
      <wsdl:output wsam:Action="http://tempuri.org/IEmulatorService/GetInventoryResponse" message="tns:IEmulatorService_GetInventory_OutputMessage" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="BasicHttpBinding_IEmulatorService" type="tns:IEmulatorService">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="PickItem">
      <soap:operation soapAction="http://tempuri.org/IEmulatorService/PickItem" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="InsertItem">
      <soap:operation soapAction="http://tempuri.org/IEmulatorService/InsertItem" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetInventory">
      <soap:operation soapAction="http://tempuri.org/IEmulatorService/GetInventory" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="IEmulatorService">
    <wsdl:port name="BasicHttpBinding_IEmulatorService" binding="tns:BasicHttpBinding_IEmulatorService">
      <soap:address location="http://localhost:8081/Service.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>