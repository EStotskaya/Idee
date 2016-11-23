//= ../../bower_components/jquery/dist/jquery.min.js

$(function() {
    var ideas = [
      {
        name: 'Mira Koval',
        date: '16.10.2016',
        time: '10:45',
        link: 'http://bla-bla-bla',
        text: 'bla-bla-bla'
      },
      {
        name: 'Mira Koval',
        date: '16.10.2016',
        time: '10:45',
        link: 'http://bla-bla-bla',
        text: 'bla-bla-bla'
      },
      {
        name: 'Mira Koval',
        date: '16.10.2016',
        time: '10:45',
        link: 'http://bla-bla-bla',
        text: 'bla-bla-bla'
      },
      {
        name: 'Mira Koval',
        date: '16.10.2016',
        time: '10:45',
        link: 'http://bla-bla-bla',
        text: 'bla-bla-bla'
      }
    ];
  
    var drawIdea = {
      initialize: function() {
        this.setVars();
        this.drawIdeas();
      },
      setVars: function() {
        this.vars = {
          ideasBlock : $('.ideas-block')
        }
      },
      drawIdeas: function() {
        var ideasHTML = '';
        ideas.forEach((item) => {
          ideasHTML += `<div class="idea">
                      <div class="idea-header">
                        <div class="idea-avatar-wrapper">
                          <img class="idea-avatar" src="img/lamp.png">
                        </div>
                        <div class="idea-information-wrapper">
                          <p class="idea-name">${item.name}</p>
                          <p class="idea-date">${item.date}|${item.time}</p>
                        </div>
                      </div>
                      <div class="idea-body">
                        <div class="idea-link">
                          <a href="#">${item.link}</a>
                        </div>
                        <div class="idea-text">${item.text}</div>
                        <img class="idea-image" src="img/lamp.png">
                      </div>
                    </div>`
        });
        this.vars.ideasBlock.append(ideasHTML);
      }
    }
    
    drawIdea.initialize();
});