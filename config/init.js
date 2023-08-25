rs.initiate({
  _id : "rp0",
  members: [
    {
      _id: 0,
      host : "mongo:27017",
      priority : 1
    }, {
      _id: 1,
      host : "mongo1:27017",
      priority : 0
    }, {
      _id: 2,
      host : "mongo2:27017",
      priority : 0
    },
  ]
});

rs.conf();
