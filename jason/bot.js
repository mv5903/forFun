const PREFIX = '-'

const Discord = require('discord.js')
const client = new Discord.Client()
const BOT_TOKEN = 'OTQwNjY3ODE2MjQ2NDAzMTIz.YgKvLA.iqnoxLhQv2e9os-5QVsCHUUatDE'

const BOT_CHANNEL = '940672532804145232'

const weather = require('openweather-apis')
const fetch = require('node-fetch')
const cron = require('cron')
const TIME_OFFSET = 5

const WEATHER_URL = 'https://api.openweathermap.org/data/2.5/onecall?lat=40.502473&lon=-74.446556&units=imperial&appid=214164ec971a2392bc3bd503b9174fff'

// Initial Login to API
client.login(BOT_TOKEN)

client.once('ready', () => {
    console.log('Discord Bot is online!')
    registerWeather()
})

client.on('message', message => {
    let messageContent = message.content
    if (!messageContent.startsWith(PREFIX) || message.author.bot) return;
    messageContent = messageContent.slice(PREFIX.length).split(" ").shift().toLowerCase()
    if (messageContent.includes('hello')) {
        send('Hello!')
    }
})

const registerWeather = () => {
    const weatherAlertEvent = new cron.CronJob('30 9 * * *', alertWeather)
    weatherAlertEvent.start()
}

const alertWeather = async () => {
    // First, fetch weather for the day
    const response = await fetch(WEATHER_URL)
    const data = await response.json()

    const temperatureThreshold = 42
    
    const hourly = data.hourly
    let lowestFound = 100
    for (var i = 0; i < 9; i++) {
        if (hourly[i].temp < temperatureThreshold && hourly[i].temp < lowestFound) {
            lowestFound = hourly[i].temp
        }
        if (hourly[i].hasOwnProperty('pop') && hourly[i].pop > .2) {
            send("There is a decent chance of rain today.")
            break
        }
    }
    send('Temperature will be below freezing today! Lowest: ' + lowestFound + '°F')
}

const send = message => {
    client.channels.cache.get(BOT_CHANNEL).send(message)
}

