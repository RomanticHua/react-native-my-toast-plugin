import React, {Component} from 'react';

import {
    StyleSheet,
    Text,
    TouchableOpacity,
} from 'react-native';

export default class Btn extends Component {
    render() {
        return (
            <TouchableOpacity style={styles.touchableOpacity}
                              onPress={this.props.onPress}>
                <Text style={styles.touchableOpacityText}>{this.props.title}</Text>
            </TouchableOpacity>
        );
    }
}

const styles = StyleSheet.create({

    touchableOpacity: {
        width: 150,
        height: 40,
        borderRadius: 5,
        marginTop: 5,
        marginBottom: 5,
        marginLeft: 10,
        marginRight: 10,
        backgroundColor: '#ff0000',
        justifyContent: 'center',
        alignItems: 'center',

    },
    touchableOpacityText: {
        color: '#fff',
        fontSize: 15,
        fontWeight: 'bold',
    },
});

