import React, {Component} from 'react'
import {StyleSheet, Text, View, ScrollView, DeviceEventEmitter} from 'react-native'
import MyToastPlugin from 'react-native-my-toast-plugin'

import Btn from './Button'

export default class App extends Component<> {
    state = {
        message: '--',
        countIndex: 0
    }

    componentDidMount() {
        console.log('componentDidMount..')
        this.listener = DeviceEventEmitter.addListener('count', (result) => {
            this.setState({
                countIndex: result.index
            })
            console.log("count: " + JSON.stringify(result))
        })
    }

    componentWillUnmount() {
        console.log('componentWillUnmount..')
        this.listener.remove()

    }

    _chatWithCallback() {
        MyToastPlugin.testCallBack('Testing', (message) => {
            this.setState({
                message
            })
        })
    }

    _chatWithPromise() {
        MyToastPlugin.testPromise('哈哈').then(result => {
            alert(JSON.stringify(result))
        })
    }

    _printActivity() {
        MyToastPlugin.printActivity().then(result => {
            alert(JSON.stringify(result))
        })
    }

    _count() {
        MyToastPlugin.count()
    }

    _stopCount() {
        MyToastPlugin.stopCount()
    }


    render() {
        return (
            <ScrollView contentContainerStyle={{flex: 1}}>
                <View style={styles.container}>
                    <Btn title='Callback' onPress={this._chatWithCallback.bind(this)}/>
                    <Btn title='Promise' onPress={() => {
                        this._chatWithPromise()
                    }}/>

                    <Btn title='printActivity' onPress={this._printActivity.bind(this)}/>
                    <Btn title='count' onPress={this._count.bind(this)}/>
                    <Btn title='stopCount' onPress={this._stopCount.bind(this)}/>
                    <Text>{this.state.message}</Text>
                    <Text>{this.state.countIndex}</Text>
                </View>
            </ScrollView>
        )
    }
}

const styles = StyleSheet.create({

    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
    },

})
