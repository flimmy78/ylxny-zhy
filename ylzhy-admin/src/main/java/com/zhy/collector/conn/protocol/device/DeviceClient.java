package com.zhy.collector.conn.protocol.device;

import com.zhy.collector.conn.protocol.Protocol;
import com.zhy.collector.conn.protocol.ResultData;
import com.zhy.collector.conn.protocol.device.listener.DeviceListener;
import com.zhy.common.utils.CommonUtil;
import com.zhy.modules.dict.entity.DeviceEntity;
import com.zhy.modules.dict.entity.LineEntity;
import io.netty.channel.socket.SocketChannel;

import java.util.*;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: DataCollector
 * @Package com.shoujiang.platform.base.conn.client
 * @Description: 采集器客户端
 * @date Date : 2018年09月01日 11:43
 */
public class DeviceClient implements DeviceListener {
    public static final int ON_LINE = 0;
    public static final int OFF_LINE = 1;

    //在线状态 0 在线 1 不在线
    private int status;
    //状态更新时间
    private long statusTime;
    //ip地址
    private String ip;
    //地址
    private String address;
    //端口
    private int port;
    //socket通道
    private SocketChannel channel;
    //dtu设备
    private DeviceEntity dtuDevice;
    //回路列表
    private Map<String, LineEntity> lineMap = new HashMap<String, LineEntity>();
    //dtu所挂接的回路对应的命令 地址对应
    private Map<String, LineDevice> lineDeviceMap = new HashMap<String, LineDevice>();

    /**
     * 开始发送指令
     * @param frequency
     */
    /*public void startSend(int frequency){
        if(dtuDevice.getProtocolType() == Protocol.PROTOCOL_TYPE_DLT645){
            sendByLine(frequency);
        }else if(dtuDevice.getProtocolType() == Protocol.PROTOCOL_TYPE_MODBUS){
            sendByRound(frequency);
        }
    }*/

    /**
     * 一次发送一个回路的全部命令
     */
    /*private void sendByLine(int frequency){
        for(Iterator<LineDevice> it = lineDeviceMap.values().iterator(); it.hasNext();){
            LineDevice device = it.next();
            while(!device.isFinished()){
                sendNextMsg(device);
                device.getWaitQueue().add(frequency);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    /***
     * 发送采集器挂架回路的一轮的某一条指令
     */
    /*private void sendByRound(int frequency){
        for(Iterator<LineDevice> it = lineDeviceMap.values().iterator(); it.hasNext();){
            LineDevice device = it.next();
            sendNextMsg(device);
            device.getWaitQueue().add(frequency);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/


    /***
     * 发送下一条指令
     * @param device
     */
    /*public void sendNextMsg(LineDevice device){
        if(getStatus() == OFF_LINE){
            //TODO...
            return;
        }
        int protocol = device.getLineEntity().getProtocol();
        if(protocol == Protocol.PROTOCOL_TYPE_MODBUS){
            if(!device.isStatus()){
                return;
            }
        }
        byte[] data = device.getNextCmd();
        channel.writeAndFlush(data);
    }*/

    /***
     * 发送下一条指令
     * @param address
     * @param protocol
     */
    /*public void sendNextMsg(String address, int protocol){
        if(getStatus() == OFF_LINE){
            //TODO...
            return;
        }
        LineDevice lineDevice = lineDeviceMap.get(address);
        if(!CommonUtil.isNull(lineDevice)){
            return;
        }
        if(protocol == Protocol.PROTOCOL_TYPE_MODBUS){
            if(!lineDevice.isStatus()){
                return;
            }
        }
        byte[] data = lineDevice.getNextCmd();
        channel.writeAndFlush(data);
    }*/

    /***
     * 更新modbus回路状态状态
     */
    /*public void updateModbusLineStatus(int protocol_type,LineDevice line){
        if (!line.isStatus()){
            line.setStatus(true);
            //唤醒消息发送
            sendNextMsg(address, protocol_type);
        }
    }*/


    private Protocol getProtocolByType(int type) {
        return Protocol.getProtocolByType(type);
    }

    /**
     * 匹配dtu设备
     * @param data
     */
    private void matchDTUDevice(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for(int i = 0 ; i < data.length; i++){
            buffer.append((char)data[i]);
        }
        String s = buffer.toString();
        if(s.startsWith(DTUProtocol.PROTOCOL_DTU_TITLE)){
            String id = s.substring(DTUProtocol.PROTOCOL_DTU_TITLE_START_INDEX, DTUProtocol.PROTOCOL_DTU_TITLE_END_INDEX);
            dtuDevice = DeviceManager.newInstance().getDeviceById(id);
            if(!CommonUtil.isNull(dtuDevice)){
                this.setDtuDevice(dtuDevice);
                lineMap = DeviceManager.newInstance().getLineMap(id);
                DeviceManager.newInstance().addIdAndIpMap(id, this.ip);
                initLineStatus(lineMap);
            }
        }
    }

    /**
     * 初始化回路状态
     * @param lineMap
     */
    private void initLineStatus(Map<String, LineEntity> lineMap) {
        for(Iterator<String> it = lineMap.keySet().iterator(); it.hasNext(); ){
            String addr = it.next();
            LineDevice device = lineDeviceMap.get(addr);
            device.setParent(this);
        }
    }

    /***
     * 更新dtu在线状态
     */
    private void updateDtuStatus() {
        setStatus(ON_LINE);
        setStatusTime((new Date()).getTime() / (1000 * 60));
    }

    public Map<String, LineEntity> getLineMap() {
        return lineMap;
    }

    /**
     * 添加回路命令列表
     * @param line
     * @param lineCmdMap
     */
    public void addLineCmdList(LineEntity line, Map<Integer, LineCmd> lineCmdMap) {
        LineDevice lineDevice = new LineDevice();
        lineDevice.setLineEntity(line);
        lineDevice.setLineCmdMap(lineCmdMap);
        lineDevice.setChannel(channel);
        lineDeviceMap.put(line.getaddress(), lineDevice);
    }

    /***
     * 按频率采集命令
     * @param frequency
     */
    /*public void collectByFrequency(int frequency) {
        for(Iterator<LineDevice> it = lineDeviceMap.values().iterator(); it.hasNext();){
            LineDevice lineDevice = it.next();
            if(lineDevice.getFrequency() != 0){
                //有一次查询没执行完 需要等待
                lineDevice.getWaitQueue().add(frequency);
            }
            LineCmd lineCmd = lineDevice.getLineCmdByFrequency(frequency);
            lineDevice.addExecCmdData(lineCmd.getCmdDatas());
            lineDevice.setFrequency(frequency);
            startSend(frequency);
        }
    }*/

    /***
     * 开始采集的事件监听
     * @param frequency
     */
    @Override
    public void startCollect(int frequency) {
        for(Iterator<LineDevice> it = lineDeviceMap.values().iterator(); it.hasNext(); ){
            LineDevice device = it.next();
            device.startCollect(frequency);
        }
    }

    /***
     * 接收到dtu客户端的消息
     * @param data
     */
    @Override
    public void msgReceive(byte[] data) {
        if(DeviceManager.newInstance().filterDTUMsg(data)){
            updateDtuStatus();
            if(data.length == DTUProtocol.REGISTER_LENGTH){
                matchDTUDevice(data);
                return;
            }
        }
        Protocol protocol = getProtocolByType(dtuDevice.getprotocol_type());
        if(!CommonUtil.isNull(protocol)){
            if(protocol.matchProtocol(data)){
                List<ResultData> results = protocol.getResultData(data);
                if(!CommonUtil.isEmpty(results)){
                    ResultData result = results.get(0);
                    String addr = result.getAddress();
                    LineDevice lineDevice = lineDeviceMap.get(addr);
                    lineDevice.msgReceive(results);
                }
            }
        }
    }

    /**
     * 接口方法 用不到
     * @param results
     */
    @Override
    public void msgReceive(List<ResultData> results) {
    }

    public int getStatus() {
        return status;
    }

    public boolean isOnline() {
        return status == ON_LINE;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }

    public DeviceEntity getDtuDevice() {
        return dtuDevice;
    }

    public void setDtuDevice(DeviceEntity dtuDevice) {
        this.dtuDevice = dtuDevice;
    }

    public long getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(long statusTime) {
        this.statusTime = statusTime;
    }

}
