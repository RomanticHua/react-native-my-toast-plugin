import React, {Component} from 'react'
import {StyleSheet, Text, View, ScrollView} from 'react-native'
import MyToastPlugin from 'react-native-my-toast-plugin'

import Btn from './Button'

export default class App extends Component<> {
    state = {
        message: '--'
    }

    _chatWithCallback() {
        MyToastPlugin.sampleMethod2('Testing', 123, (message) => {
            this.setState({
                message
            })
        })
    }

    _chatWithPromise() {
        MyToastPlugin.toast('哈哈').then(result => {
            alert(JSON.stringify(result))
        })
    }

    render() {
        return (
            <ScrollView contentContainerStyle={{flex: 1}}>
                <View style={styles.container}>
                    <Btn title='Callback' onPress={this._chatWithCallback.bind(this)}/>
                    <Btn title='Promise' onPress={() => {
                        this._chatWithPromise()
                    }}/>
                    <Text>{this.state.message}</Text>
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
